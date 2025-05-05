package model;

public class Participant {
    private int id;
    private String name;
    private String email;
    private int score; 

    public Participant(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.score = 0; 
    }

    // Overloaded constructor 
    public Participant(String name, String email) {
        this.name = name;
        this.email = email;
        this.score = 0;
    }

    public Participant(int participantId, String name) {
        this.id = participantId;
        this.name = name;
    }

	
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Method to get the score
    public int getScore() {
        return score;
    }

    
    public void setScore(int score) {
        this.score = score;
    }

    
    public void addScore(int points) {
        this.score += points;
    }

    
    @Override
    public String toString() {
        return "Participant ID: " + id + "\nName: " + name + "\nEmail: " + email + "\nScore: " + score;
    }

	public int getParticipantId() {
		
		return id;
	}

	public void setParticipantId(int int1) {
		
		this.id=int1;
		
	}
}
