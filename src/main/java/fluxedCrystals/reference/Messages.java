package fluxedCrystals.reference;

public class Messages {

	public static final class Commands {

		private static final String COMMAND_PREFIX = "commands." + Reference.LOWERCASE_MOD_ID + ".";

		public static final String BASE_COMMAND_USAGE = COMMAND_PREFIX + "usage";

		public static final String PLAYER_NOT_FOUND_ERROR = COMMAND_PREFIX + "player-not-found.error";
		public static final String NO_ITEM = COMMAND_PREFIX + "no-item.error";

		public static final String RESET_SEEDS_USAGE = COMMAND_PREFIX + Names.Commands.RESET_SEEDS + ".usage";
		public static final String SEED_FROM_CURRENT_USAGE = COMMAND_PREFIX + Names.Commands.SEED_FROM_CURRENT + ".usage";
		public static final String SEED_FROM_CURRENT_SUCCESS = COMMAND_PREFIX + Names.Commands.SEED_FROM_CURRENT + ".success";

	}

}
