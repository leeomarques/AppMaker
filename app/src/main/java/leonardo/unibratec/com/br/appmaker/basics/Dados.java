package leonardo.unibratec.com.br.appmaker.basics;

import java.io.Serializable;

/**
 * Created by leonardo on 19/12/17.
 */

public class Dados implements Serializable {

    private Integer id;
    private String nome;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
