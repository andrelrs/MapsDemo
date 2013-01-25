package com.example.mapsdemo;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MostraAlunosActivity extends FragmentActivity {

	private GoogleMap mapa;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mostra_alunos);
		
		SupportMapFragment fragment = 
				(SupportMapFragment) getSupportFragmentManager().
					findFragmentById(R.id.map);
		
		mapa = fragment.getMap();
		
		adicionaAlunoNoMapa();
	}
	
	private void adicionaAlunoNoMapa() {
		AlunoDAO dao = new AlunoDAO();
		List<Aluno> alunos = dao.getLista();
		dao.close();

		for (Aluno aluno : alunos) {
			LatLng coordenada = 
				new GeoCoderUtil(this).getCoordenada(aluno.getEndereco());

			mapa.addMarker(new MarkerOptions().position(coordenada)
				.title(aluno.getNome()).snippet(aluno.getEndereco()));
		}
	}

}
