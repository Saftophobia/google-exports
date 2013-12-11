package clause;

import java.util.HashMap;
import java.util.Map;

/**
 * This class ensures unique standardize apart indexicals are created.
 * 
 */
public class StandardizeApartIndexicalFactory {
	// initialized list of indexicals to be compared
	private static Map<Character, Integer> _assignedIndexicals = new HashMap<Character, Integer>();

	public static StandardizeApartIndexical newStandardizeApartIndexical(
			Character preferredPrefix) {
		// get char value of input prefix
		char ch = preferredPrefix.charValue();
		// check if prefix is not lower case
		if (!(Character.isLetter(ch) && Character.isLowerCase(ch))) {
			throw new IllegalArgumentException("Preferred prefix :"
					+ preferredPrefix + " must be a valid a lower case letter.");
		}

		StringBuilder sb = new StringBuilder();
		// append the two prefixes
		synchronized (_assignedIndexicals) {
			Integer currentPrefixCnt = _assignedIndexicals.get(preferredPrefix);
			if (null == currentPrefixCnt) { // increase count if null
				currentPrefixCnt = 0;
			} else {
				currentPrefixCnt += 1;
			}
			_assignedIndexicals.put(preferredPrefix, currentPrefixCnt);
			sb.append(preferredPrefix);
			for (int i = 0; i < currentPrefixCnt; i++) {
				sb.append(preferredPrefix); // append output string
			}
		}
		// return output prefix
		return new StandardizeApartIndexicalImpl(sb.toString());
	}
}
// output class format 
class StandardizeApartIndexicalImpl implements StandardizeApartIndexical {
	private String prefix = null;
	private int index = 0;

	// constructor with prefix setter
	public StandardizeApartIndexicalImpl(String prefix) {
		this.prefix = prefix;
	}

	// START-StandardizeApartIndexical
	public String getPrefix() { // return the prefix
		return prefix;
	}

	public int getNextIndex() { // return the next index
		return index++;
	}
	// END-StandardizeApartIndexical

}