package net.librarian.app.web.bean;

import net.librarian.app.service.RepositoryService;
import net.librarian.app.web.model.Repo;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;

/**
 * Created by siliev on 12/18/13.
 */
public class ReposWebBean {

    private transient RepositoryService repoService;

    public void enable() {

    }

    public List<Repo> getRepos() {

        System.out.println("============= called bean");

        return repoService.list();
    }

    @Required
    public void setRepoService(RepositoryService repoService) {
        this.repoService = repoService;
    }
}
