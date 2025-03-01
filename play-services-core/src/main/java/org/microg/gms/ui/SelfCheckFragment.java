/*
 * Copyright (C) 2013-2017 microG Project Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.microg.gms.ui;

import static android.os.Build.VERSION.SDK_INT;

import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import org.microg.tools.selfcheck.InstalledPackagesChecks;
import org.microg.tools.selfcheck.SelfCheckGroup;
import org.microg.tools.selfcheck.SystemChecks;
import org.microg.tools.ui.AbstractSelfCheckFragment;
import org.microg.tools.ui.AbstractSettingsActivity;

import java.util.List;

public class SelfCheckFragment extends AbstractSelfCheckFragment {

    @Override
    protected void prepareSelfCheckList(List<SelfCheckGroup> checks) {
//        checks.add(new RomSpoofSignatureChecks());
        checks.add(new InstalledPackagesChecks());
//        if (SDK_INT >= 23) {
//            List<String> permissions = new ArrayList<>();
//            permissions.add(ACCESS_COARSE_LOCATION);
//            permissions.add(ACCESS_FINE_LOCATION);
//            if (SDK_INT >= 29) {
//                permissions.add(ACCESS_BACKGROUND_LOCATION);
//            }
//            permissions.add(READ_EXTERNAL_STORAGE);
//            permissions.add(WRITE_EXTERNAL_STORAGE);
//            permissions.add(GET_ACCOUNTS);
//            permissions.add(READ_PHONE_STATE);
//            permissions.add(RECEIVE_SMS);
//            checks.add(new PermissionCheckGroup(permissions.toArray(new String[0])) {
//                @Override
//                public void doChecks(Context context, ResultCollector collector) {
//                    super.doChecks(context, collector);
//                    PackageManager pm = context.getPackageManager();
//                    try {
//                        PermissionInfo info = pm.getPermissionInfo("android.permission.SYSTEM_ALERT_WINDOW", 0);
//                        CharSequence permLabel = info.loadLabel(pm);
//                        collector.addResult(
//                                context.getString(org.microg.tools.ui.R.string.self_check_name_permission, permLabel),
//                                Settings.canDrawOverlays(context) ? Result.Positive : Result.Negative,
//                                context.getString(org.microg.tools.ui.R.string.self_check_resolution_permission),
//                                fragment -> {
//                                    Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + context.getPackageName()));
//                                    startActivityForResult(intent, 42);
//                                }
//                        );
//                    } catch (Exception e) {
//                        Log.w("SelfCheckPerms", e);
//                    }
//                }
//            });
//        }
        if (SDK_INT >= Build.VERSION_CODES.M) {
            checks.add(new SystemChecks());
        }
//        checks.add(new NlpOsCompatChecks());
//        checks.add(new NlpStatusChecks());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        reset(LayoutInflater.from(getContext()));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        reset(LayoutInflater.from(getContext()));
        super.onActivityResult(requestCode, resultCode, data);
    }

    public static class AsActivity extends AbstractSettingsActivity {
        public AsActivity() {
            showHomeAsUp = true;
        }

        @Override
        protected Fragment getFragment() {
            return new SelfCheckFragment();
        }
    }
}
