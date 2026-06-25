class ApiConstants {
  // Use a const bool to determine the environment at compile time
  // For production builds, this should be true.
  static const bool isProduction = bool.fromEnvironment('dart.vm.product');

  // Replace with your actual deployed backend URL (e.g., https://api.urbanwear.com)
  static const String productionBaseUrl = 'https://your-production-url.com/api';
  
  // For local development
  // 10.0.2.2 is the special alias for localhost when using Android Emulator
  // For physical devices or iOS simulator, use the machine's local IP address (e.g., 192.168.1.10)
  static const String localBaseUrl = 'http://10.0.2.2:8080/api';

  static String get baseUrl => isProduction ? productionBaseUrl : localBaseUrl;
}
