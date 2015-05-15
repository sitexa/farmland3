package com.sitexa.post.data;

import com.sitexa.user.data.User;

/**
 * ParticipantId entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class ParticipantId implements java.io.Serializable {

    // Fields

    private Activity activity;
    private User user;

    // Constructors

    /**
     * default constructor
     */
    public ParticipantId() {
    }

    public ParticipantId(Activity activity, User user) {
        this.activity = activity;
        this.user = user;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof ParticipantId))
            return false;
        ParticipantId castOther = (ParticipantId) other;

        return ((this.getActivity() == castOther.getActivity()) || (this
                .getActivity() != null
                && castOther.getActivity() != null && this.getActivity()
                .equals(castOther.getActivity())))
                && ((this.getUser() == castOther.getUser()) || (this
                .getUser() != null
                && castOther.getUser() != null && this.getUser()
                .equals(castOther.getUser())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result
                + (getActivity() == null ? 0 : this.getActivity().hashCode());
        result = 37 * result
                + (getUser() == null ? 0 : this.getUser().hashCode());
        return result;
    }

}