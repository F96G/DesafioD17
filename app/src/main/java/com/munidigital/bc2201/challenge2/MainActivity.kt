package com.munidigital.bc2201.challenge2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.munidigital.bc2201.challenge2.databinding.ActivityMainBinding
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    lateinit private var context: Context
    lateinit private var  viewModel: MainViewModel

    //Esta aplicacion se basa en el capitulo de los simpsons https://www.youtube.com/watch?v=boXb5XRRarA para buscar un poco de humor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this

        binding.rvChat.layoutManager = LinearLayoutManager(this)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
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

    //Cambio el menu del toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.maint_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //en caso de seleccionar algun item
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.iDeslog -> {//Se desloguea y vuelve a la pantalla de logueo
                intent = Intent(context, LoginActivity::class.java)
                intent.putExtra(LoginActivity.KEY, true)
                startActivity(intent)
                finish()
            }
            R.id.iReload -> viewModel.refresh()
        }
        return super.onOptionsItemSelected(item)
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

    private fun manejarListaVacia(chatList: MutableList<String>, binding: ActivityMainBinding){
        //Cuando la lista esta vacia se muestra un empty
        if (chatList.isEmpty())
            binding.tvEmptyView.visibility = View.VISIBLE
        else
            binding.tvEmptyView.visibility = View.GONE
    }
}