package fr.isika.cda.entities.activities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id_activity")
public class CustomActivity extends Activity {

}
