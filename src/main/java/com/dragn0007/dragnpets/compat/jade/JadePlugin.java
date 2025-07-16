package com.dragn0007.dragnpets.compat.jade;

import com.dragn0007.dragnpets.compat.jade.gender.*;
import com.dragn0007.dragnpets.entities.cat.OCat;
import com.dragn0007.dragnpets.entities.dog.ODog;
import com.dragn0007.dragnpets.entities.fox.OFox;
import com.dragn0007.dragnpets.entities.ocelot.OOcelot;
import com.dragn0007.dragnpets.entities.parrot.OParrot;
import com.dragn0007.dragnpets.entities.wolf.OWolf;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

@WailaPlugin
public class JadePlugin implements IWailaPlugin {

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerEntityComponent(new CatGenderTooltip(), OCat.class);
        registration.registerEntityComponent(new DogGenderTooltip(), ODog.class);
        registration.registerEntityComponent(new FoxGenderTooltip(), OFox.class);
        registration.registerEntityComponent(new OcelotGenderTooltip(), OOcelot.class);
        registration.registerEntityComponent(new WolfGenderTooltip(), OWolf.class);
        registration.registerEntityComponent(new ParrotGenderTooltip(), OParrot.class);
    }
}
