package fr.isika.cda.entities.activities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "activity_id")
public class CustomActivity extends Activity {

}
