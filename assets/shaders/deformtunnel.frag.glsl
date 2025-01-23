varying vec2 v_texCoords;
varying vec4 v_color;
varying vec4 v_mix_color;

uniform float u_time;
uniform float u_timemod;
uniform sampler2D u_texture;
//uniform vec2 u_resolution;
//uniform vec2 u_mouse;

void main() {
//    vec2 p = (-iResolution.xy + 2.0*fragCoord)/iResolution.y;
    // means: inv_aspect_ration + 2 * fragCoord.xy
    // lets try it
    vec2 p = 1. - (v_texCoords * 2.);
//    vec2 p = 9./16. + 2 * v_texCoords;
    p = v_texCoords;

    p *= 0.75;

    float a = atan( p.y, p.x );
    float r = sqrt( dot(p ,p) );

    a += sin(0.5 * r - 0.5 * (u_time * u_timemod) );

    float h = 0.5 + 0.5 * cos(9.0 * a);

    float s = smoothstep(0.4, 0.5, h);

    vec2 uv;
    uv.x = (u_time * u_timemod) + 1.0/(r + .1 * s);
    uv.y = 3.0 * a / 3.1416;

    vec3 col = texture( u_texture, uv ).xyz;

    float ao = smoothstep(0.0,0.3,h)-smoothstep(0.5,1.0,h);
    col *= 1.0 - 0.6*ao*r;
    col *= r;

    gl_FragColor = vec4( col, 1.0 );
}
