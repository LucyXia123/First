<?php
/* rename file .jpg => .png */
$dir = '.';

$patterns = array(0=>'/\.jpg/');
$replacements = array(0=>'.png');

if (is_dir($dir)) {
  if ($dh = opendir($dir)) {
    while (($file = readdir($dh)) !== false) {
      if ($file !== "." && $file !== "..") {
        $name = preg_replace($patterns, $replacements, $file);
        if ($name !== $file) {
          rename($file, $name);          
        }
      }
    }
  }
}
