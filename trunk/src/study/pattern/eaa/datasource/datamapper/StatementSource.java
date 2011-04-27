package eaa.datasource.datamapper;

public interface StatementSource {
    String sql(); 
    Object[] parameters(); 
}
