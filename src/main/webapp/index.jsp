<!doctype html>
<html lang="en">
	<head>
		<meta charset="UTF-8" />
		<title>Mirana Framework Project</title>
	</head>
	<body>
		<h1>Hello, Mirana!</h1>
	</body>
	<script>
		var add = function (a, b, c) {
			int count = a + b;
			return function() {
				return count + c;
			}
		}
	</script>
</html>