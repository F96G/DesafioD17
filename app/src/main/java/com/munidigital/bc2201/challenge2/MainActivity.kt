package com.munidigital.bc2201.challenge2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.munidigital.bc2201.challenge2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvChat.layoutManager = LinearLayoutManager(this)
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val adapter = ChatAdapter()


        binding.rvChat.adapter = adapter

        viewModel.mensajeList.observe(this){
                mensajeList -> adapter.submitList(mensajeList)

            manejarListaVacia(mensajeList, binding)
            //LLeva el scroll a la ultima posicion como en wp
            binding.rvChat.scrollToPosition(mensajeList.size-2)
        }


        binding.ivSend.setOnClickListener {
            sendMessage(binding, viewModel)
        }
    }

    private fun sendMessage(binding:ActivityMainBinding, viewModel: MainViewModel){
        when{
            (binding.etMessage.text.isNotEmpty() && viewModel.yaRespondi) -> {
                viewModel.sendMensaje(binding.etMessage.text.toString())
                binding.etMessage.text.clear()
            }

            (!viewModel.yaRespondi) -> Toast.makeText(this, "Espere la respuesta", Toast.LENGTH_SHORT).show()

            else -> Toast.makeText(this, "Escriba un mensaje por favor", Toast.LENGTH_SHORT).show()
        }
    }

    private fun manejarListaVacia(chatList: MutableList<Mensaje>, binding: ActivityMainBinding){
        //Cuando la lista esta vacia se muestra un empty
        if (chatList.isEmpty())
            binding.tvEmptyView.visibility = View.VISIBLE
        else
            binding.tvEmptyView.visibility = View.GONE
    }
}