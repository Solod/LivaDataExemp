package com.solodilov.lifecycle.bases;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.solodilov.lifecycle.R;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public abstract class BaseActivity extends AppCompatActivity {
    private static int[] mResAnim;

    public static <T> Fragment addFragment(AppCompatActivity activity
            , Class<T> clazz            // Class of fragment
            , int container             // layout
            , @Nullable Bundle args     // Arguments
            , boolean backstack         // Add to backstack
            , boolean replace           // false - add, true - replace
            , boolean animation        // animation on/off
            , int[] resAnim) {
        mResAnim = resAnim;
        return addFragment(activity, clazz, container, args, backstack, replace, animation);
    }

    public static <T> Fragment addFragment(AppCompatActivity activity
            , Class<T> clazz            // Class of fragment
            , int container)             // layout
    {
        return addFragment(activity, clazz, container, null, true, true, false);
    }

    public static <T> Fragment addFragment(AppCompatActivity activity
            , Class<T> clazz            // Class of fragment
            , int container             // layout
            , boolean backstack)         // Add to backstack
    {
        return addFragment(activity, clazz, container, null, backstack, true, false);
    }

    public static <T> Fragment addFragment(AppCompatActivity activity
            , Class<T> clazz            // Class of fragment
            , int container             // layout
            , @Nullable Bundle args     // Arguments
            , boolean backstack         // Add to backstack
            , boolean replace           // false - add, true - replace
            , boolean animation)        // animation on/off
    {
        Fragment fragment = activity.getSupportFragmentManager().findFragmentByTag(clazz.getCanonicalName());
        if (fragment == null) {
            try {
                Constructor<?> cons = clazz.getConstructor();
                fragment = (Fragment) cons.newInstance();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }

            if (fragment == null) return null;

            if (args != null) {
                fragment.setArguments(args);
            }
        } else {

            if (fragment.isAdded()) {
                return fragment;
            }

            if (args != null && fragment instanceof BaseFragment) {
                ((BaseFragment) fragment).setBundle(args);
            }
        }

        FragmentTransaction transaction = activity.getSupportFragmentManager()
                .beginTransaction();

        /*if (fragment.isAdded()) {
                transaction.remove(fragment);
            }*/

        if (animation) {
            if (mResAnim == null) {
                transaction
                        .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left
                                , R.anim.slide_in_left, R.anim.slide_out_right);
            } else if (mResAnim.length == 2) {
                transaction
                        .setCustomAnimations(mResAnim[0], mResAnim[1]);
            } else if (mResAnim.length == 4) {
                transaction
                        .setCustomAnimations(mResAnim[0], mResAnim[1], mResAnim[2], mResAnim[3]);
            }
        }

        if (replace) {
            transaction.replace(container, fragment, fragment.getClass().getCanonicalName());
        } else {
            transaction.add(container, fragment, fragment.getClass().getCanonicalName());
        }

        if (backstack) {
            transaction.addToBackStack(clazz.getCanonicalName());
        }

        transaction.commitAllowingStateLoss();
        return fragment;
    }
}
