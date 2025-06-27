package application.models.modelsInterfaceDAO;

import application.models.modelsClass.Bibliothecaire;

public interface AuthentificationInterfaceDAO {
	public boolean registerBibliothecaire(Bibliothecaire bibliothecaire);
	public int loginBibliothecaire(String nameOrEmail, String password);

}
