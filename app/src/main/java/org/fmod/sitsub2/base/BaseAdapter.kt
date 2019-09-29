package org.fmod.sitsub2.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import org.fmod.sitsub2.util.errorLog
import java.lang.Exception
import java.lang.reflect.Constructor
import java.lang.reflect.Modifier
import java.lang.reflect.ParameterizedType
import kotlin.properties.Delegates


private const val TYPE_HEADER = 0
private const val TYPE_FOOTER = 1
private const val TYPE_ITEM = 2
/**
 * 普通Adapter，支持头部尾部
 *
 * @param mList 展示的列表
 */
abstract class BaseAdapter<T: BaseAdapter.BaseViewHolder, U>(var mList: List<U>): RecyclerView.Adapter<BaseAdapter.BaseViewHolder>() {

    var mOnItemClickListener: ((pos:Int) -> Unit)? = null
    var mOnItemLongClickListener: ((pos:Int) -> Boolean)? = null
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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when(viewType) {
            TYPE_HEADER -> {
                headerView?.setOnClickListener {
                    mOnHeaderClickListener?.invoke()
                }
                return BaseViewHolder(headerView as View)
            }
            TYPE_FOOTER -> {
                footerView?.setOnClickListener {
                    mOnFooterClickListener?.invoke()
                }
                return BaseViewHolder(footerView as View)
            }
            else -> {
                val res = createViewHolder(LayoutInflater.from(parent.context).inflate(getLayoutId(), parent, false))
                val pos = if(headerView != null)
                    res.adapterPosition - 1
                else
                    res.adapterPosition
                setOnListenerOnCreate(res as T, pos, mList[pos])
                res.itemView.setOnClickListener {
                    mOnItemClickListener?.invoke(pos)
                }
                res.itemView.setOnLongClickListener {
                    mOnItemLongClickListener?.invoke(pos) ?: false
                }
                res
            }
        }
    }

    /**
     * 使用反射获取BaseViewHolder子类实例
     */
    @Suppress("UNCHECKED_CAST")
    private fun createViewHolder(itemView: View): BaseViewHolder {
        val type = javaClass.genericSuperclass//获取父类Type
        if(type is ParameterizedType) {
            val classViewHolder = type.actualTypeArguments[0] as Class<*>
            try {
                val cons: Constructor<*>
                return if(classViewHolder.isMemberClass && !Modifier.isStatic(classViewHolder.modifiers)){//内部且非静态类
                    //获取内部类构造函数必须通过外部类
                    cons = classViewHolder.getDeclaredConstructor(javaClass, View::class.java)
                    cons.isAccessible = true
                    cons.newInstance(this, itemView) as BaseViewHolder
                } else {
                    cons = classViewHolder.getDeclaredConstructor(View::class.java)
                    cons.isAccessible = true
                    cons.newInstance(this, itemView) as BaseViewHolder
                }
            } catch (e: Exception) {
                errorLog(e.message)
            }
        }
        return BaseViewHolder(itemView)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when(holder.itemViewType) {
            TYPE_ITEM -> {
                val pos = if(headerView != null)
                    position - 1
                else
                    position
                onBindViewHolder(holder as T, pos, mList[pos])
            }
            TYPE_HEADER -> {
                onBindHeader(holder as T)
            }
            TYPE_FOOTER -> {
                onBindFooter(holder as T)
            }
        }
    }

    protected abstract fun onBindViewHolder(holder: T, position: Int, value: U)

    protected open fun onBindHeader(holder: T) {}

    protected open fun onBindFooter(holder: T) {}

    protected open fun setOnListenerOnCreate(holder: T, position: Int, value: U) {}

    /**
     * 继承此ViewHolder目的是绑定itemView到子类属性上
     * 在大量onBind中使用kotlin安卓控件的extension会影响一定的性能
     *
     * header和footer数量只有一个，刷新对性能影响不大，直接使用BaseViewHolder
     *
     * 对于itemView，使用反射获取子类实例
     */

    open class BaseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

}