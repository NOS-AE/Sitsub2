package org.fmod.sitsub2.view

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.layout_filter.view.*
import org.fmod.sitsub2.R
import org.fmod.sitsub2.util.log

/**
 * Create by NOSAE on 2019/11/10
 */
class FilterSheetDialogFragment: BottomSheetDialogFragment() {

    private lateinit var mBehavior: BottomSheetBehavior<*>
    private lateinit var mView: View

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //setStyle(DialogFragment.STYLE_NORMAL, R.style.BottomDialogTheme)
        val res = super.onCreateDialog(savedInstanceState)
        res.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        return res
    }

    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)
        if (!this::mView.isInitialized) {
            mView = View.inflate(context, R.layout.layout_filter, null)
            initView()
        }
        dialog.setContentView(mView)
        val parent = (mView.parent as View)
        parent.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
        mBehavior = BottomSheetBehavior.from(parent)
        mBehavior.isHideable = true
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStart() {
        super.onStart()
        mBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    override fun onDestroy() {
        super.onDestroy()
        (mView.parent as ViewGroup).removeView(mView)
    }

    private fun initView() {
        mView.filter_toolbar.setNavigationOnClickListener {
            mBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        }
    }


}