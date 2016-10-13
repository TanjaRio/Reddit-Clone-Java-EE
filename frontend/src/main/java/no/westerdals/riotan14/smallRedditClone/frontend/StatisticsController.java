package no.westerdals.riotan14.smallRedditClone.frontend;

import no.westerdals.riotan14.smallRedditClone.entity.User;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Tanja Guntvedt Rio
 * PG5100 - Enterprise Programmering 1
 * Westerdals Oslo ACT
 */

@Named
@Singleton
public class StatisticsController {

    @Inject
    private EntityManager em;

}
