package com.munidigital.bc2201.challenge2

import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.munidigital.bc2201.challenge2.databinding.ChatListaBinding


class ChatAdapter(): androidx.recyclerview.widget.ListAdapter<Mensaje, ChatAdapter.ChatViewHolder>(DiffCallbak){
    //Permite saber que item se agrego o edito o se borro
    companion object DiffCallbak : DiffUtil.ItemCallback<Mensaje>(){
        override fun areItemsTheSame(oldItem: Mensaje, newItem: Mensaje): Boolean {
            return oldItem.message == newItem.message
        }

        override fun areContentsTheSame(oldItem: Mensaje, newItem: Mensaje): Boolean {
            return oldItem == newItem
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatAdapter.ChatViewHolder {
        val binding = ChatListaBinding.inflate(LayoutInflater.from(parent.context))

        //Le pasamos el layout a nuestro viewHolder
        return ChatViewHolder(binding)
    }

    //Esta clase pinta los elementos del view holder en la posicion indicada
    override fun onBindViewHolder(holder: ChatAdapter.ChatViewHolder, position: Int) {
        val chat = getItem(position)
        holder.bind(chat,position)
    }
    //Le paso el view para que luego pinte los elementos
    inner class ChatViewHolder(private val binding: ChatListaBinding): RecyclerView.ViewHolder(binding.root) {
        //Son todos los view que componen al adapter y que deben pintarse
        fun bind(chat: Mensaje, position: Int) {
            binding.tvMessage.text = chat.message
            if (0 == position%2){
                binding.tvMessage.setBackgroundColor(Color.parseColor("#6FD4E8BE"))
                binding.tvMessage.gravity = Gravity.END
            }else{
                binding.tvMessage.setBackgroundColor(Color.parseColor("#5B85F1FF"))
            }
        }
    }

}