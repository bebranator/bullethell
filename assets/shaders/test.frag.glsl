varying vec2 v_texCoords;
varying vec4 v_color;
varying vec4 v_mix_color;

uniform float u_time;
uniform sampler2D u_texture;
//uniform vec2 u_resolution;
//uniform vec2 u_mouse;

void main( )
{
//    vec2  p = (2. * v_texCoords - 1.) * vec2(u_resolution.x / u_resolution.y, 1.);
//
//    vec2  t = vec2(atan(p.y, p.x) / 3.1415, 1. / length(p));
//    vec2  s = u_time / 60 * vec2(.1, 1);
//    vec2  z = vec2(3, 1);
//    float m = t.y + 0.5;
//
//    gl_FragColor = vec4(texture(u_texture, t).xyz, 1);
//    gl_FragColor = texture(u_texture, v_texCoords);
//    vec4 tex = texture(u_texture, v_texCoords);
//    tex.a = u_time;
//    gl_FragColor = vec4(p.y, 0., 0., 1.);
//    gl_FragColor = vec4(u_time / 120, 0., 0.,  1.);
    vec2 p = v_texCoords;

    gl_FragColor.w = length(p = (p) - .5);
    gl_FragColor = texture( u_texture, vec2(atan(p.y,p.x), .2/ gl_FragColor.w) + u_time )*gl_FragColor.w;
}
