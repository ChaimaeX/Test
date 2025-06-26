package Model;

import java.util.ArrayList;
import java.util.List;

public class Compte {
    // Supprimer le 'static' pour que chaque compte ait sa propre liste
    private List<AddInfo> informations = new ArrayList<>();

    public void add(AddInfo info) {
        if (info != null) {
            informations.add(info);
        }
    }

    public List<AddInfo> getInformations() {
        return informations;
    }

    // Méthode helper pour récupérer le dernier solde
    public int getCurrentBalance() {
        if (informations.isEmpty()) {
            return 0;
        }
        return informations.get(informations.size() - 1).getBalance();
    }
}