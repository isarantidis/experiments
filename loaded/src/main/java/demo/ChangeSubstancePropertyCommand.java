package demo;

public class ChangeSubstancePropertyCommand extends Command {

	@Override
	public String getCommandType() {
		return ChangeSubstancePropertyCommand.class.getSimpleName();
	}

}
