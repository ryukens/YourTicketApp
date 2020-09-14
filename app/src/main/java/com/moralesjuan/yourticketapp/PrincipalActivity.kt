package com.moralesjuan.yourticketapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.get
import com.google.android.gms.ads.MobileAds
import androidx.navigation.ui.AppBarConfiguration as AppBarConfiguration1

class PrincipalActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration1

    companion object {
        var globalVarId = ""
        var globalVarEmail = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        MobileAds.initialize(this){}

        PrincipalActivity.Companion.globalVarId = intent.getStringExtra(EXTRA_ID).toString()
        PrincipalActivity.Companion.globalVarEmail = intent.getStringExtra(EXTRA_EMAIL).toString()

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration1(
            setOf(
                R.id.nav_home, R.id.nav_profile, R.id.nav_categorias, R.id.nav_cupones_guardados
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
//        navView[4].setOnClickListener{
//            startActivity(Intent(this, LoginActivity::class.java))
//            finish()
//        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.principal, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId;
        if(id == R.id.nav_log_out){
            Toast.makeText(this, "Log Out", Toast.LENGTH_SHORT).show();
            var intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}