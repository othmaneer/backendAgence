package ma.agence.voyage.service;

import ma.agence.voyage.entity.Login;
import ma.agence.voyage.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService{
    @Autowired
    private LoginRepository loginRepository;
    @Override
    public Login ajouterLogin(Login a) {
        return loginRepository.save(a);
    }

    @Override
    public List<Login> getLogin() {
        return loginRepository.findAll();
    }

    @Override
    public void modifierLogin(Login v) {

        Optional<Login> r= loginRepository.findById(v.getIdAdmin());

        if(r.isPresent())
        {
            Login c= r.get();

            c.setUsername(v.getUsername());
            c.setMdp(v.getMdp());
            loginRepository.save(c);

        }
    }

    @Override
    public void supprimerLogin(int id) {
        loginRepository.deleteById(id);
    }

    @Override
    public boolean existLogin(String username, String mdp) {
        Optional<Login> loginOptional = loginRepository.findByUsernameAndMdp(username, mdp);
        return loginOptional.isPresent();
    }
}
