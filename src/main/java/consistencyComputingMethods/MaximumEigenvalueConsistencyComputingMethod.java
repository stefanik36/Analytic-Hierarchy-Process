package consistencyComputingMethods;

import Jama.EigenvalueDecomposition;
import Jama.Matrix;

public class MaximumEigenvalueConsistencyComputingMethod extends ConsistencyComputeMethod {

	@Override
	public double compute(Matrix matrix) {
		EigenvalueDecomposition eig = matrix.eig();
		double[] eigenvalues = eig.getRealEigenvalues();
		int n = eigenvalues.length;
		if (n < 2) {
			return 0;
		}
		double maxEig = eigenvalues[0];
		for (int i = 1; i < n; i++) {
			if (maxEig < eigenvalues[i]) {
				maxEig = eigenvalues[i];
			}
//			System.out.println(eigenvalues[i]);
		}
//		System.out.println(maxEig);
		consistencyIndex = Math.abs(maxEig - n)/(n-1);
		consistencyRatio = consistencyIndex/randomIndex[n];
//		System.out.println("CI: "+consistencyIndex);
//		System.out.println("RI: "+consistencyRatio);
		
		return consistencyRatio;
	}

	@Override
	public String toString() {
		return "maximum eigenvalue";
	}

}
