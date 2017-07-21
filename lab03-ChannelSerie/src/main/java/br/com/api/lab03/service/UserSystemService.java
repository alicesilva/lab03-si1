package br.com.api.lab03.service;

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
	
	public UserSystem searchUserToEmail(String email) {
		return usuarioRepository.searchUserToEmail(email);
	}

	public UserSystem registerSerieToProfile(Integer id, Serie serie) {
		UserSystem foundUser = usuarioRepository.findOne(id);
		
		Serie seriesFoundInProfile = searchSerieInCollection(foundUser.getSeriesProfile(), serie);
		Serie seriesFoundInWatchlist = searchSerieInCollection(foundUser.getSeriesWacthList(), serie);
		if( seriesFoundInProfile != null || seriesFoundInWatchlist != null){
			return null;
		}else{
			foundUser.getSeriesProfile().add(serie);
			usuarioRepository.save(foundUser);
			return foundUser;
		}
		
		
	}

	public UserSystem registerSerieToWatchlist(Integer id, Serie serie) {
		UserSystem foundUser = usuarioRepository.findOne(id);
		
		Serie seriesFoundInProfile = searchSerieInCollection(foundUser.getSeriesProfile(), serie);
		Serie seriesFoundInWatchlist = searchSerieInCollection(foundUser.getSeriesWacthList(), serie);
		if( seriesFoundInProfile != null || seriesFoundInWatchlist != null){
			return null;
		}else{
			foundUser.getSeriesWacthList().add(serie);
			usuarioRepository.save(foundUser);
			return foundUser;
		}
	}

	public UserSystem addNoteEvaluationToSerie(Integer id, Serie serie) {
		UserSystem foundUser = usuarioRepository.findOne(id);
		Serie seriesFoundInProfile = searchSerieInCollection(foundUser.getSeriesProfile(), serie);
		seriesFoundInProfile.setNote(serie.getNote());
		usuarioRepository.save(foundUser);
		return foundUser;
	}

	public UserSystem addLastChapterWatchedTheSerie(Integer id, Serie serie) {
		UserSystem foundUser = usuarioRepository.findOne(id);
		Serie seriesFoundInWatchlist = searchSerieInCollection(foundUser.getSeriesProfile(), serie);
		seriesFoundInWatchlist.setLastWatchedChapter(serie.getLastWatchedChapter());
		usuarioRepository.save(foundUser);
		return foundUser;
	}

	public UserSystem removeSeriesFromProfile(Integer id, Serie serie) {
		UserSystem foundUser = usuarioRepository.findOne(id);
		Serie seriesFoundInProfile = searchSerieInCollection(foundUser.getSeriesProfile(), serie);
		foundUser.getSeriesProfile().remove(seriesFoundInProfile);
		usuarioRepository.save(foundUser);
		return foundUser;
		
	}

	public UserSystem registerWatchlistSerieOnProfile(Integer id, Serie serie) {
		UserSystem foundUser = usuarioRepository.findOne(id);
		Serie seriesFoundInWatchlist = searchSerieInCollection(foundUser.getSeriesWacthList(), serie);
		foundUser.getSeriesWacthList().remove(seriesFoundInWatchlist);
		foundUser.getSeriesProfile().add(seriesFoundInWatchlist);
		usuarioRepository.save(foundUser);
		return foundUser;
	}
	
	private Serie searchSerieInCollection(Set<Serie> seriesProfile, Serie serie) {
		for (Serie series : seriesProfile) {
				if(series.getName().equals(serie.getName())){
					return series;
				}
		}
		return null;
	}

	public Serie getEvaluationNote(Integer id, Serie serie) {
		UserSystem foundUser = usuarioRepository.findOne(id);
		Serie seriesFoundInProfile = searchSerieInCollection(foundUser.getSeriesProfile(), serie);
		return seriesFoundInProfile;
	}

	public Serie getlastWatchedChapter(Integer id, Serie serie) {
		UserSystem foundUser = usuarioRepository.findOne(id);
		Serie seriesFoundInProfile = searchSerieInCollection(foundUser.getSeriesProfile(), serie);
		return seriesFoundInProfile;
	}
	
	

}
