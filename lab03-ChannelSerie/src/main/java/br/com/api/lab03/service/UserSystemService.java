package br.com.api.lab03.service;

import java.util.Collection;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.lab03.model.Serie;
import br.com.api.lab03.model.UserSystem;
import br.com.api.lab03.repository.UserSystemRepository;

@Service
public class UserSystemService {
	
	@Autowired
	UserSystemRepository usuarioRepository;

	public UserSystem userRegistration(UserSystem usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public UserSystem searchUserToPassword(String password) {
		return usuarioRepository.searchUserToPassword(password);
	}

	public UserSystem buscarUser(UserSystem usuario) {
		return usuarioRepository.findOne(usuario.getId());
	}
	
	public UserSystem buscarUserId(Integer id) {
		return usuarioRepository.findOne(id);
	}
	

	public UserSystem registerSerieToProfile(Integer id, Serie serie) {
		UserSystem usuarioEncontrado = usuarioRepository.findOne(id);
		
		Serie s = buscarSerie(usuarioEncontrado.getSeriesProfile(), serie);
		Serie s1 = buscarSerie(usuarioEncontrado.getSeriesWacthList(), serie);
		if( s != null || s1 != null){
			return null;
		}else{
			usuarioEncontrado.getSeriesProfile().add(serie);
			usuarioRepository.save(usuarioEncontrado);
			return usuarioEncontrado;
		}
		
		
	}

	public UserSystem registerSerieToWatchlist(Integer id, Serie serie1) {
		UserSystem usuarioEncontrado = usuarioRepository.findOne(id);
		
		Serie s = buscarSerie(usuarioEncontrado.getSeriesProfile(), serie1);
		Serie s1 = buscarSerie(usuarioEncontrado.getSeriesWacthList(), serie1);
		if( s != null || s1 != null){
			return null;
		}else{
			usuarioEncontrado.getSeriesWacthList().add(serie1);
			usuarioRepository.save(usuarioEncontrado);
			return usuarioEncontrado;
		}
	}

	public UserSystem addNoteEvaluationToSerie(Integer id, Serie serie) {
		UserSystem usuarioEncontrado = usuarioRepository.findOne(id);
		Serie serie1 = buscarSerie(usuarioEncontrado.getSeriesProfile(), serie);
		serie1.setNota(serie.getNota());
		usuarioRepository.save(usuarioEncontrado);
		
		return usuarioEncontrado;
	}

	private Serie buscarSerie(Set<Serie> seriesProfile, Serie serie) {
		for (Serie series : seriesProfile) {
				if(series.getName().equals(serie.getName())){
					return series;
				}
		}
		return null;
	}

	public UserSystem addLastChapterWatchedTheSerie(Integer id, Serie serie) {
		UserSystem usuarioEncontrado = usuarioRepository.findOne(id);
		Serie serie1 = buscarSerie(usuarioEncontrado.getSeriesProfile(), serie);
		serie1.setLastChapter(serie.getLastChapter());
		usuarioRepository.save(usuarioEncontrado);
		
		return usuarioEncontrado;
		
	}

	public UserSystem removeSeriesFromProfile(Integer id, Serie serie) {
		UserSystem usuarioEncontrado = usuarioRepository.findOne(id);
		Serie serie1 = buscarSerie(usuarioEncontrado.getSeriesProfile(), serie);
		
		Serie serie2 = buscarSerie(usuarioEncontrado.getSeriesWacthList(), serie);
		usuarioEncontrado.getSeriesProfile().remove(serie1);
		usuarioRepository.save(usuarioEncontrado);

		return usuarioEncontrado;
		
	}

	public int teste2(Integer id, Serie serie) {
		UserSystem usuarioEncontrado = usuarioRepository.findOne(id);
		Serie serie1 = buscarSerie(usuarioEncontrado.getSeriesProfile(), serie);
		return serie1.getNota();
	}

	public UserSystem registerWatchlistSerieOnProfile(Integer id, Serie serie1) {
		UserSystem usuarioEncontrado = usuarioRepository.findOne(id);

		Serie s1 = buscarSerie(usuarioEncontrado.getSeriesWacthList(), serie1);
		usuarioEncontrado.getSeriesWacthList().remove(s1);
		usuarioEncontrado.getSeriesProfile().add(s1);
		usuarioRepository.save(usuarioEncontrado);
		return usuarioEncontrado;
	}
	
	

}
