package ma.agence.voyage.service;

import ma.agence.voyage.entity.Login;

import java.util.List;

public interface LoginService {

    public Login ajouterLogin(Login a);

    List<Login> getLogin();

    void  modifierLogin(Login v);

    public void supprimerLogin(int id);

    public boolean existLogin(String email , String mdp);
}
