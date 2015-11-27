package com.example.piroacc.myapplication.fetchers.objects;

/**
 * Created by PiroACC on 2015-11-14.
 */
public class PokemonEntity {
    private int idpokemon;
    private String name;

    public PokemonEntity(int idpokemonint, String name) {
        this.idpokemon=idpokemonint;
        this.name=name;
    }


    public int getIdpokemon() {
        return idpokemon;
    }

    public void setIdpokemon(int idpokemon) {
        this.idpokemon = idpokemon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

/*    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PokemonEntity that = (PokemonEntity) o;

        if (idpokemon != that.idpokemon) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idpokemon;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }*/
}
