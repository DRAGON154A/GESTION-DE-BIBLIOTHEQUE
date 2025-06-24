package com.models.modelsInterfaceDAO;

import com.models.modelsClass.Bibliothecaire;

public interface AuthentificationInterfaceDAO {
	

	public boolean registerBibliothecaire(Bibliothecaire bibliothecaire);
	public int loginBibliothecaire(String nameOrEmail,String password);
}
