package sim

import kick.Commitable
import kick.Submitable
import sim.KAnimator.KAnimation

/** A bundle of a animation, a text and something to commit or submit. */
sealed class Bundle (val text : String, val animation : KAnimation) {}

/** A bundle of something to commit, a descriptive text and a animation. */
class KCommitBundle (
   val commitable : Commitable, text : String = "", animation : KAnimation = KAnimation.void()
) : Bundle( text, animation ) {}

/** A bundle of something to submit, a descriptive text and a animation. */
class KSubmitBundle (
   val submitable : Submitable, text : String = "", animation : KAnimation = KAnimation.void()
) : Bundle( text, animation ) {}
