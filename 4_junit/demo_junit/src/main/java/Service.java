public class Service {

    private Repository repository;

    public Service(Repository repository) {
        this.repository = repository;
    }

    public boolean insert(String string) {
        //eseguo le operazioni poi ritorno uno stato boolean
        return repository.checkStatus();
    }

    public String checkDatabaseId() {
        return "Sto usando db con id: " + repository.getDatabaseId();
    }
}
