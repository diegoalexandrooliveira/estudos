package br.com.diego.estudos.movierecommendation.movies.infrastructure;

import lombok.AllArgsConstructor;

public enum MoviesList {

    SAVING_PRIVATE_RYAN("tt0120815"),
    DUNKIRK("tt5013056"),
    FURY("tt2713180"),
    INGLORIOUS_BASTARDS("tt0361748"),
    SCHINDLER_LIST("tt0108052"),
    PEARL_HARBOR("tt0213149"),
    THE_PIANIST("tt0253474"),
    ENEMY_AT_THE_GATES("tt0215750"),
    AMERICAN_SNIPER("tt2179136"),
    HACKSAW_RIDGE("tt2119532"),

    HOBBIT("tt0903624"),
    THE_LORD_OF_THE_RINGS("tt0120737"),
    HARRY_POTTER("tt0241527"),
    PIRATES_OF_THE_CARIBBEAN("tt0325980"),
    AVATAR("tt0499549"),
    KING_KONG("tt0360717"),
    THE_CHRONICLES_OF_NARNIA("tt0363771"),
    THE_MUMMY("tt0120616"),
    STAR_WARS("tt0076759"),

    THE_FAULT_IN_OUR_STARS("tt2582846"),
    ME_BEFORE_YOU("tt2674426"),
    THE_NOTEBOOK("tt0332280"),
    TITANIC("tt0120338"),
    DEAR_JOHN("tt0989757"),
    A_STAR_IS_BORN("tt1517451"),
    SILVER_LININGS_PLAYBOOK("tt1045658"),
    A_WALK_TO_REMEMBER("tt0281358"),
    ONE_DAY("tt1563738"),

    JOHN_WICK("tt2911666"),
    FAST_AND_FURIOUS("tt0232500"),
    THE_EQUALIZER("tt0455944"),
    MAD_MAX("tt1392190"),
    THE_DARK_KNIGHT("tt0468569"),
    SKYFALL("tt1074638"),
    THE_BOURNE_ULTIMATUM("tt0440963");

    private String imdb_id;

    public String getImdb_id() {
        return this.imdb_id;
    }

    MoviesList(String imdb_id) {
        this.imdb_id = imdb_id;
    }
}
