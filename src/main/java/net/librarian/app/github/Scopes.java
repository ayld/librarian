package net.librarian.app.github;

import com.google.common.base.Joiner;

/**
 * Created with IntelliJ IDEA.
 * User: siliev
 * Date: 12/6/13
 * Time: 4:40 PM
 */
public enum Scopes {

    /**
     * Public read-only access (includes public user profile info, public repo info, and gists)
     * */
    NONE(""),

    /**
     * Read/write access to profile info only. Note: this scope includes user:email and user:follow.
     * */
    USER("user"),

    /**
     * Read access to a user’s email addresses.
     * */
    USER_EMAIL("user:email"),

    /**
     * Access to follow or unfollow other users.
     * */
    USER_FOLLOW("user:follow"),

    /**
     * Read/write access to public repos and organizations.
     * */
    PUBLIC_REPO("public_repo"),

    /**
     * Read/write access to public and private repos and organizations.
     * */
    REPO("repo"),

    /**
     * Read/write access to public and private repository commit statuses.
     * This scope is only necessary to grant other users or services access to private repository commit statuses without granting access to the code.
     * The repo and public_repo scopes already include access to commit status for private and public repositories, respectively.
     * */
    REPO_STATUS("repo:status"),

    /**
     * 	Delete access to adminable repositories.
     * */
    REPO_DELETE("delete_repo"),

    /**
     * 	Read access to a user’s notifications. Repo is accepted too.
     * */
    NOTIFICATIONS("notifications"),

    /**
     * Write access to gists.
     * */
    GIST("gist");

    private final String code;

    private Scopes(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static String asString(Scopes... scopes) {
        return Joiner.on(",").join(scopes);
    }
}
