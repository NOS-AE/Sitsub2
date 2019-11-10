package org.fmod.sitsub2.base

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import org.fmod.sitsub2.util.inflate
import org.fmod.sitsub2.util.log
import kotlin.properties.Delegates


private const val TYPE_HEADER = 0
private const val TYPE_FOOTER = 1
private const val TYPE_ITEM = 2
/**
 * 普通Adapter，支持头部尾部
 *
 * @param mList 展示的列表
 */
abstract class BaseAdapter<U>(protected var mList: List<U>): RecyclerView.Adapter<BaseAdapter<U>.ViewHolder>() {

    /*var mOnItemClickListener: ((pos: Int, item: U) -> Unit)? = null
    var mOnItemLongClickListener: ((pos: Int, item: U) -> Boolean)? = null*/

    var onSetListener: ((holder: ViewHolder) -> Unit) = { }
    var mOnHeaderClickListener: (()->Unit)? = null
    var mOnFooterClickListener: (()->Unit)? = null

    protected var headerView by Delegates.observable<View?>(null) { _, old, new ->
        if(old == null && new != null){
            notifyItemInserted(0)
        } else if(old != null && new == null) {
            notifyItemRemoved(0)
        }
    }

    protected var footerView by Delegates.observable<View?>(null) { _, old, new ->
        if(old == null && new != null) {
            notifyItemInserted(itemCount - 1)
        } else if(old != null && new == null) {
            notifyItemRemoved(itemCount - 1)
        }
    }

    @LayoutRes
    abstract fun getLayoutId(): Int

    override fun getItemCount(): Int {
        var size = mList.size
        if(headerView != null)
            size++
        if(footerView != null)
            size++
        return size
    }

    override fun getItemViewType(position: Int): Int {
        return if(headerView == null && footerView == null)
            TYPE_ITEM
        else if(headerView != null && position == 0)
            TYPE_HEADER
        else if(footerView != null && position == itemCount - 1)
            TYPE_FOOTER
        else
            TYPE_ITEM
    }

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when(viewType) {
            TYPE_HEADER -> {
                headerView?.setOnClickListener {
                    mOnHeaderClickListener?.invoke()
                }
                return ViewHolder(headerView as View)
            }
            TYPE_FOOTER -> {
                footerView?.setOnClickListener {
                    mOnFooterClickListener?.invoke()
                }
                return ViewHolder(footerView as View)
            }
            else -> {
                val res = ViewHolder(parent.inflate(getLayoutId()))
                onCreateViewHolder(res)
                onSetListener(res)
                /*res.itemView.setOnClickListener {
                    mOnItemClickListener?.invoke(pos, mList[pos])
                }
                res.itemView.setOnLongClickListener {
                    mOnItemLongClickListener?.invoke(pos, mList[pos]) ?: false
                }*/
                res
            }
        }
    }



    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when(holder.itemViewType) {
            TYPE_ITEM -> {
                val pos = if(headerView != null)
                    position - 1
                else
                    position
                onBindViewHolder(holder, pos, mList[pos])
            }
            TYPE_HEADER -> {
                onBindHeader(holder)
            }
            TYPE_FOOTER -> {
                onBindFooter(holder)
            }
        }
    }

    protected open fun onCreateViewHolder(holder: ViewHolder) {}

    protected abstract fun onBindViewHolder(holder: ViewHolder, position: Int, value: U)

    protected open fun onBindHeader(holder: ViewHolder) {}

    protected open fun onBindFooter(holder: ViewHolder) {}

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), LayoutContainer {
        override val containerView: View? = itemView
        fun getPos(): Int = if (headerView != null)
                adapterPosition - 1
            else
                adapterPosition
    }

}