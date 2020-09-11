package com.example.submissiontiga

import android.content.Context
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.submissiontiga.ui.detail.FollowersFragment
import com.example.submissiontiga.ui.detail.FollowingFragment
import com.example.submissiontiga.ui.detail.RepoFragment

class SectionsPagerAdapter(private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val TAB_TITLES = intArrayOf(R.string.followers, R.string.following, R.string.repository)
    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment =
                FollowersFragment()
            1 -> fragment =
                FollowingFragment()
            2 -> fragment =
                RepoFragment()
        }
        return fragment as Fragment
    }

    @Nullable
    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return 3
    }

}