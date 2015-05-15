package com.sitexa.farm.rest.join;

import com.sitexa.farm.data.Faction;
import com.sitexa.farm.data.Land;
import com.sitexa.farm.data.Service;
import com.sitexa.farm.rest.FarmlanderAction;
import com.sitexa.user.data.Member;

import java.util.ArrayList;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2010-4-22
 * Time: 10:30:11
 */
public class FactionAction extends FarmlanderAction {
    protected String id;//farmingActionId;
    protected Land land;
    protected Faction faction;
    protected List<Faction> factions = new ArrayList<Faction>();
    protected Service service;
    protected List<Service> services = new ArrayList<Service>();

    protected Member member;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Land getLand() {
        return land;
    }

    public void setLand(Land land) {
        this.land = land;
    }

    public Faction getFaction() {
        return faction;
    }

    public void setFaction(Faction faction) {
        this.faction = faction;
    }

    public List<Faction> getFactions() {
        return factions;
    }

    public void setFactions(List<Faction> factions) {
        this.factions = factions;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }
}
