package com.example.ajaib_test.recyclerviewadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.ajaib_test.R
import de.hdodenhof.circleimageview.CircleImageView
import org.w3c.dom.Text

class RecyclerViewAdapterDetailUserRepos(val avatar_url:String,val namelist:ArrayList<String>,val descriptionlist:ArrayList<String>,val starlist:ArrayList<String>,val lastupdatelist:ArrayList<String>,val context: Context ):RecyclerView.Adapter<RecyclerViewAdapterDetailUserRepos.Holder>() {
    class Holder(view:View):RecyclerView.ViewHolder(view) {
        lateinit var  circleImageView: CircleImageView
        lateinit var txtname:TextView
        lateinit var txtdesc:TextView
        lateinit var txtstar:TextView
        lateinit var txtlastupdate:TextView
        init {
            circleImageView = view.findViewById(R.id.image_show_repos)
            txtname = view.findViewById(R.id.txtname_show_repos)
            txtdesc = view.findViewById(R.id.txtdescription_show_repos)
            txtstar = view.findViewById(R.id.txtstar_show_repos)
            txtlastupdate = view.findViewById(R.id.txtupdate_show_repos)

        }
        fun bind(avatar_url: String){
            Glide.with(itemView).load(avatar_url).transition(
                DrawableTransitionOptions.withCrossFade()).centerCrop().into(circleImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.show_repos_detail_user,parent,false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(avatar_url)
        holder.txtname.setText(namelist.get(position))
        holder.txtdesc.setText(descriptionlist.get(position))
        holder.txtstar.setText(starlist.get(position))
        holder.txtlastupdate.setText("Update "+lastupdatelist.get(position))
    }

    override fun getItemCount(): Int {
        return namelist.size
    }
}