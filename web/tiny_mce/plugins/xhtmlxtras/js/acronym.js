 /**
 * $Id: acronym.js,v 1.1.1.1 2010/11/15 10:16:16 oscar Exp $
 *
 * @author Moxiecode - based on work by Andrew Tetlaw
 * @copyright Copyright © 2004-2008, Moxiecode Systems AB, All rights reserved.
 */

function init() {
	SXE.initElementDialog('acronym');
	if (SXE.currentAction == "update") {
		SXE.showRemoveButton();
	}
}

function insertAcronym() {
	SXE.insertElement('acronym');
	tinyMCEPopup.close();
}

function removeAcronym() {
	SXE.removeElement('acronym');
	tinyMCEPopup.close();
}

tinyMCEPopup.onInit.add(init);
