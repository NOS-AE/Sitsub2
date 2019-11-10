package org.fmod.sitsub2.base.listener

import com.google.android.material.tabs.TabLayout

/**
 * Create by NOSAE on 2019/10/24
 */
abstract class OnTabSelectListener: TabLayout.OnTabSelectedListener {

    override fun onTabReselected(tab: TabLayout.Tab?) {}

    override fun onTabSelected(tab: TabLayout.Tab?) {}

    override fun onTabUnselected(tab: TabLayout.Tab?) {}
}