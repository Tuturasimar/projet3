package fr.isika.cda.entities.activities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;

import fr.isika.cda.entities.common.AbsenceTypeEnum;

@Entity
@PrimaryKeyJoinColumn(name = "id_activity")
public class Absence extends Activity {

	@Enumerated(EnumType.STRING)
	private AbsenceTypeEnum absenceType;

}
