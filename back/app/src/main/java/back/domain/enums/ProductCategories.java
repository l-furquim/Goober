package back.domain.enums;

public enum ProductCategories {
    CASA("CASA"),
    ROUPAS_ACESSORIOS("ROUPAS E ACESSORIOS"),
    ELETRONICO("ELETRONICOS"),
    SAUDE_BELEZA("SAUDE E BELEZA"),
    GAMER("GAMER"),
    INFANTIL("INFANTIL"),
    ESPORTE("ESPORTE"),
    COMIDA("COMIDA"),
    UTEIS("UTEIS");


    private String nome;

    private ProductCategories(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return nome;
    }

    public static ProductCategories fromNome(String nome) {
        for (ProductCategories category : ProductCategories.values()) {
            if (category.getNome().equalsIgnoreCase(nome)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Categoria de produto desconhecida: " + nome);
    }
}
