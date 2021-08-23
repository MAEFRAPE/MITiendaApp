package com.example.myapplication.UI.Activities

import android.animation.Animator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.airbnb.lottie.LottieAnimationView
import com.example.myapplication.Data.Models.Comment
import com.example.myapplication.Data.Models.Producto
import com.example.myapplication.Data.Models.Storeinfo
import com.example.myapplication.R
import com.example.myapplication.UI.viewmodels.SplasViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var splash: LottieAnimationView

    private val splashViewModel: SplasViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
    }

    override fun onStart() {
        super.onStart()
        splash=findViewById(R.id.SplashAnimation)

        splashViewModel.insert(
            listOf(
                Comment(1,"https://cdn.icon-icons.com/icons2/1378/PNG/512/avatardefault_92824.png","Carlos Beltran","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."),
                Comment(2,"https://cdn.icon-icons.com/icons2/1378/PNG/512/avatardefault_92824.png","Pepito Perez","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."),
                Comment(3,"https://cdn.icon-icons.com/icons2/1378/PNG/512/avatardefault_92824.png","Manuel Franco","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."),
                Comment(4,"https://cdn.icon-icons.com/icons2/1378/PNG/512/avatardefault_92824.png","Pedro Piedras","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."),
                Comment(5,"https://cdn.icon-icons.com/icons2/1378/PNG/512/avatardefault_92824.png","Esteban Dido","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."),
                Comment(6,"https://cdn.icon-icons.com/icons2/1378/PNG/512/avatardefault_92824.png","Elsa Patico","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."),
                Comment(7,"https://cdn.icon-icons.com/icons2/1378/PNG/512/avatardefault_92824.png","Elvis Cocho","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."),

                ),
            listOf(
                Producto(1,"https://comfandivirtual.com.co/supermercados/70485-large_default/arroz-kilo.jpg","Arroz Roa","$2500","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."),
                Producto(2,"https://comfandivirtual.com.co/supermercados/1021-large_default/aceite-liquido-olio-soya-vegetal-frasco-2000ml.jpg","Aceite OleoSoya","$3500","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."),
                Producto(3,"https://metrocolombiafood.vteximg.com.br/arquivos/ids/159831-1000-1000/7702007043402-1.jpg?v=636670251334730000","Chocolate","$4000","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."),
                Producto(4,"https://grupodiana.co/wp-content/uploads/2019/12/btn_grano_2.png","Frijol","$6000","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."),
                Producto(5,"https://jumbocolombiafood.vteximg.com.br/arquivos/ids/3510553-1000-1000/7702511002933.jpg?v=637273105687100000","lenteja","$1000","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."),
            ),
            Storeinfo(
                1,
                "El nombre de la tienda",
                "https://www.gadae.com/blog/wp-content/uploads/mi-tienda.jpg",
                "Calle falsa 123 Bogota",
                "300415234"
            )
        )
        /*splash.imageAssetsFolder= "imagenes"*/
        splash.playAnimation()
        splashViewModel.loginIn()
        splash.addAnimatorListener(object: Animator.AnimatorListener {

            override fun onAnimationStart(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {

                splashViewModel.user.observe(this@SplashScreenActivity,{user ->
                    if (user != null){
                        val intent = Intent(this@SplashScreenActivity, LoginActivity::class.java)
                        startActivity(intent)
                    }else{

                        val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
                        startActivity(intent)
                    }
                    finish()
                }
                )
            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationRepeat(animation: Animator?) {

            }


        })
    }
}