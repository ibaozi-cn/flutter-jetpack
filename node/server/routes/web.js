var express = require('express');
var router = express.Router();

router.get('/:location?', function (req, res, next) {
  res.json({
      'data':'test'
  });
});

module.exports = router;