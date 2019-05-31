package code.model

import _root_.net.liftweb.jpa.RequestVarEM
import _root_.org.scala_libs.jpa.LocalEMF
import javax.persistence.Persistence
import javax.persistence.EntityManager


object JPAUtility extends LocalEMF("jpaconexion") with RequestVarEM
