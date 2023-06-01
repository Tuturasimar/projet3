package fr.isika.cda.utils;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public final class SessionUtils {

	public static String getUserLoginFromSession() {
		HttpSession session = getSession();
		// Lire une propriété depuis la session 
		return (String) session.getAttribute("login");
	}

	public static void setUserLoginIntoSession(final String login) {
		HttpSession session = getSession();
		// Ajoute une entrée (clé, valeur) dans la map de la session
		session.setAttribute("login", login);
	}
	
	public static boolean isUserLoggedIn() {
		// Si pas de lgoin mémorisé en session -> personne n'est connecté !!
		return getUserLoginFromSession() != null
				&& !getUserLoginFromSession().isBlank();
	}
	
	public static void resetSession() {
		HttpSession session = getSession();
		session.invalidate();
	}
	
	
	
	private static HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
	}
	
	private SessionUtils() {
		// Pour interdire d'instancier cette classe
	}

}
