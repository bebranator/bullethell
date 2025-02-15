package bullethell.game;

public enum Difficulty {
    easy("easy", .8f), normal("normal", 1f),
    hard("hard", 1.2f), expert("lunatic", 1.4f), // rn use lunatic texture
    // lunatic �����!!!!!!!!!!!
    lunatic("lunatic", 1.5f); // lunatic is other extra

    public final float bonusModifier;
    public final String name;
    Difficulty(String name, float bonusModifier) {
        this.bonusModifier = bonusModifier;
        this.name = name;
    }

    public String difficultyDescription() {
        return switch (this) {
            case lunatic -> "{SPEED=0.25}[GRAY]�� �������";
            case expert -> "���� �������� ���� ������, [RED]�� ������[WHITE].";
            case hard -> "�������� ������, ��?";
            case normal -> "���������";
            case easy -> "��� ����";
        };
    }
}
