interface Player {
    public Grid aiChoices = new AIChoices();
    public Grid aiShips = new AIShips();
    public PlayerChoices playerChoices = new PlayerChoices();
    public Grid getGrid();

    public void fire(Grid opponentGrid);
}