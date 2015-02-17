package com.proyecto.urudatamobile;

    import android.support.v7.app.ActionBarActivity;
    import android.content.Intent;
    import android.os.Bundle;
    import android.view.Menu;
    import android.support.v4.app.Fragment;
    import android.view.MenuInflater;
    import android.view.MenuItem;
    import android.view.View;
    import android.widget.EditText;
    import android.view.LayoutInflater;
    import android.view.ViewGroup;



 public class MainActivity extends ActionBarActivity {

        /**
         * Called when the activity is first created.
         * @param savedInstanceState If the activity is being re-initialized after
         * previously being shut down then this Bundle contains the data it most
         * recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
         */

        public static boolean isQuit = false;


        public static void setQuit(boolean flag){
            isQuit=flag;
        }

     @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.container, new PlaceholderFragment()).commit();
            }


        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu items for use in the action bar
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_main, menu);
            return super.onCreateOptionsMenu(menu);
        }

        public void outById(View view){

            Intent intent = new Intent(this,com.proyecto.urudatamobile.OutNameActivity.class);
            EditText editTextName = (EditText) findViewById(R.id.id_outsourcer);
            String name = editTextName.getText().toString();
            EditText editTextPass = (EditText) findViewById(R.id.pass_outsourcer);
            String pass = editTextPass.getText().toString();
            intent.putExtra("name_outsourcer", name);
            intent.putExtra("pass_outsourcer", pass);
            startActivity(intent);
        }
         @Override
         public boolean onOptionsItemSelected(MenuItem item) {

             switch (item.getItemId()) {

                 case R.id.action_close:
                     System.out.println("Cerrando  el MAIN....");
                     finish();

                 default:
                     return super.onOptionsItemSelected(item);
             }
         }
         @Override
         protected void onRestart() {
                     super.onRestart();
                     if( isQuit ){
                         finish();
                     }
                 }


public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
}