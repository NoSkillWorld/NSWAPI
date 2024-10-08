package fr.noskillworld.api.honorranks;

import java.util.List;
import java.util.UUID;

public interface HonorRanksHandler {

    /**
     * Initialize the player's honor data
     *
     * @param uuid the uuid of the player
     * @param name the name of the player
     */
    void init(UUID uuid, String name);

    /**
     * Set the player points to a certain value
     *
     * @param uuid   the uuid of the player
     * @param amount the amount of points to set
     */
    void setPlayerPoints(UUID uuid, long amount);

    /**
     * Append a certain amount of points for a specific player
     *
     * @param uuid   the uuid of the player
     * @param amount the amount of points to give
     */
    void gainPlayerPoints(UUID uuid, long amount);

    /**
     * Resets the current player rank to default (0)
     *
     * @param uuid the uuid of the player
     */
    void resetPlayerRank(UUID uuid);

    /**
     * Uprank the player if the player have enough points
     *
     * @param uuid the uuid of the player
     */
    void upRankPlayer(UUID uuid);

    /**
     * Force uprank the player, this is most used by admins
     *
     * @param uuid the uuid of the player
     */
    void forceUpRankPlayer(UUID uuid);

    /**
     * Get the amount of points the player currently has
     *
     * @param uuid the uuid of the player
     * @return long
     */
    long getPlayerPoints(UUID uuid);

    /**
     * Get the required amount of points the player
     * need to have for upgrading to the nex rank
     * Returns 0 if the player's next rank is the highest
     *
     * @param uuid the uuid of the player
     * @return long
     */
    long getPointsNeeded(UUID uuid);

    /**
     * Get the current rank of the player
     *
     * @param uuid the uuid of the player
     * @return HonorRanks
     */
    HonorRanks getPlayerRank(UUID uuid);

    /**
     * Get the current rank id of the player
     *
     * @param uuid the uuid of the player
     * @return int
     */
    int getPlayerRankId(UUID uuid);

    /**
     * Get the next rank of the player
     * Returns null if the player's next rank is the highest
     *
     * @param uuid the uuid of the player
     * @return HonorRanks
     */
    HonorRanks getNextPlayerRank(UUID uuid);

    /**
     * Get a list of the player's passed ranks
     *
     * @param uuid the uuid of the player
     * @return List<HonorRanks>
     */
    List<HonorRanks> getPreviousPlayerRanks(UUID uuid);

    /**
     * Get a colored name of the player's current rank
     *
     * @param uuid the uuid of the player
     * @return String
     */
    String getDisplayName(UUID uuid);

    /**
     * Get a colored prefix of the player's current rank
     *
     * @param uuid the uuid of the player
     * @return String
     */
    String getPrefix(UUID uuid);

    /**
     * Get a nice list of the player's rank
     * with useful information
     *
     * @param uuid the uuid of the player
     * @return String
     */
    String getRanks(UUID uuid);

    /**
     * Get the text format of the current player rank
     *
     * @param uuid the uuid of the player
     * @return String
     */
    String getPlayerRankFormat(UUID uuid);

    /**
     * Get the text formatting of a specific rank
     *
     * @param rank the rank to get the format from
     * @return String
     */
    String getRankFormat(HonorRanks rank);

    /**
     * Returns true if the player has a rank
     * returns false if not
     *
     * @param uuid the uuid of the player
     * @return boolean
     */
    boolean isRanked(UUID uuid);
}
