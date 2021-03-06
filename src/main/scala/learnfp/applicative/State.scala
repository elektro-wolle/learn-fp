package learnfp.applicative

import learnfp.functor.State
import learnfp.functor.StateInstance._

object StateInstance {
  implicit def stateApplicativeInstance[S, A, R] = new Applicative[A, R, ({type E[X] = State[S, X]})#E] {
    override def pure[A](a: A): State[S, A] = ???
    override def <*>(fx: State[S, A => R])(a: State[S, A]): State[S, R] = ???
  }

  class StatePureApplicativeOps[A](a:A) {
    def pure[S] = stateApplicativeInstance[S, Unit, A].pure(a)
  }

  implicit def stateToApplicativeOps[S, A, R](a:State[S, A => R]) = new FxApplicativeOps[A, R, ({type E[X] = State[S, X]})#E](a)
  implicit def stateToPureOps[A](a:A) = new StatePureApplicativeOps[A](a)
}
